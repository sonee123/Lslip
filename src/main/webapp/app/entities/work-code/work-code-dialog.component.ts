import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WorkCode } from './work-code.model';
import { WorkCodePopupService } from './work-code-popup.service';
import { WorkCodeService } from './work-code.service';
import { User, UserService } from '../../shared';
import { WorkCenterMaster, WorkCenterMasterService } from '../work-center-master';

@Component({
    selector: 'jhi-work-code-dialog',
    templateUrl: './work-code-dialog.component.html'
})
export class WorkCodeDialogComponent implements OnInit {

    workCode: WorkCode;
    isSaving: boolean;

    users: User[];

    workcentermasters: WorkCenterMaster[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private workCodeService: WorkCodeService,
        private userService: UserService,
        private workCenterMasterService: WorkCenterMasterService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.userService.query()
            .subscribe((res: HttpResponse<User[]>) => { this.users = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.workCenterMasterService.query()
            .subscribe((res: HttpResponse<WorkCenterMaster[]>) => { this.workcentermasters = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.workCode.id !== undefined) {
            this.subscribeToSaveResponse(
                this.workCodeService.update(this.workCode));
        } else {
            this.subscribeToSaveResponse(
                this.workCodeService.create(this.workCode));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<WorkCode>>) {
        result.subscribe((res: HttpResponse<WorkCode>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: WorkCode) {
        this.eventManager.broadcast({ name: 'workCodeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackUserById(index: number, item: User) {
        return item.id;
    }

    trackWorkCenterMasterById(index: number, item: WorkCenterMaster) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-work-code-popup',
    template: ''
})
export class WorkCodePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workCodePopupService: WorkCodePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.workCodePopupService
                    .open(WorkCodeDialogComponent as Component, params['id']);
            } else {
                this.workCodePopupService
                    .open(WorkCodeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
