import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WorkCenterMaster } from './work-center-master.model';
import { WorkCenterMasterPopupService } from './work-center-master-popup.service';
import { WorkCenterMasterService } from './work-center-master.service';
import { LayslipKeyHeader, LayslipKeyHeaderService } from '../layslip-key-header';

@Component({
    selector: 'jhi-work-center-master-dialog',
    templateUrl: './work-center-master-dialog.component.html'
})
export class WorkCenterMasterDialogComponent implements OnInit {

    workCenterMaster: WorkCenterMaster;
    isSaving: boolean;

    layslipkeyheaders: LayslipKeyHeader[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private workCenterMasterService: WorkCenterMasterService,
        private layslipKeyHeaderService: LayslipKeyHeaderService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.layslipKeyHeaderService.query()
            .subscribe((res: HttpResponse<LayslipKeyHeader[]>) => { this.layslipkeyheaders = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.workCenterMaster.id !== undefined) {
            this.subscribeToSaveResponse(
                this.workCenterMasterService.update(this.workCenterMaster));
        } else {
            this.subscribeToSaveResponse(
                this.workCenterMasterService.create(this.workCenterMaster));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<WorkCenterMaster>>) {
        result.subscribe((res: HttpResponse<WorkCenterMaster>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: WorkCenterMaster) {
        this.eventManager.broadcast({ name: 'workCenterMasterListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackLayslipKeyHeaderById(index: number, item: LayslipKeyHeader) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-work-center-master-popup',
    template: ''
})
export class WorkCenterMasterPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workCenterMasterPopupService: WorkCenterMasterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.workCenterMasterPopupService
                    .open(WorkCenterMasterDialogComponent as Component, params['id']);
            } else {
                this.workCenterMasterPopupService
                    .open(WorkCenterMasterDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
