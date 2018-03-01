import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Layslipheader } from './layslipheader.model';
import { LayslipheaderPopupService } from './layslipheader-popup.service';
import { LayslipheaderService } from './layslipheader.service';

@Component({
    selector: 'jhi-layslipheader-dialog',
    templateUrl: './layslipheader-dialog.component.html'
})
export class LayslipheaderDialogComponent implements OnInit {

    layslipheader: Layslipheader;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private layslipheaderService: LayslipheaderService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.layslipheader.id !== undefined) {
            this.subscribeToSaveResponse(
                this.layslipheaderService.update(this.layslipheader));
        } else {
            this.subscribeToSaveResponse(
                this.layslipheaderService.create(this.layslipheader));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Layslipheader>>) {
        result.subscribe((res: HttpResponse<Layslipheader>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Layslipheader) {
        this.eventManager.broadcast({ name: 'layslipheaderListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-layslipheader-popup',
    template: ''
})
export class LayslipheaderPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipheaderPopupService: LayslipheaderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.layslipheaderPopupService
                    .open(LayslipheaderDialogComponent as Component, params['id']);
            } else {
                this.layslipheaderPopupService
                    .open(LayslipheaderDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
