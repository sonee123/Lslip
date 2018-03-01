import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LayslipKeyHeader } from './layslip-key-header.model';
import { LayslipKeyHeaderPopupService } from './layslip-key-header-popup.service';
import { LayslipKeyHeaderService } from './layslip-key-header.service';
import { Layslipheader, LayslipheaderService } from '../layslipheader';
import { LayslipRollDetails, LayslipRollDetailsService } from '../layslip-roll-details';

@Component({
    selector: 'jhi-layslip-key-header-dialog',
    templateUrl: './layslip-key-header-dialog.component.html'
})
export class LayslipKeyHeaderDialogComponent implements OnInit {

    layslipKeyHeader: LayslipKeyHeader;
    isSaving: boolean;

    layslipheaders: Layslipheader[];

    laysliprolldetails: LayslipRollDetails[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private layslipKeyHeaderService: LayslipKeyHeaderService,
        private layslipheaderService: LayslipheaderService,
        private layslipRollDetailsService: LayslipRollDetailsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.layslipheaderService.query()
            .subscribe((res: HttpResponse<Layslipheader[]>) => { this.layslipheaders = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.layslipRollDetailsService.query()
            .subscribe((res: HttpResponse<LayslipRollDetails[]>) => { this.laysliprolldetails = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.layslipKeyHeader.id !== undefined) {
            this.subscribeToSaveResponse(
                this.layslipKeyHeaderService.update(this.layslipKeyHeader));
        } else {
            this.subscribeToSaveResponse(
                this.layslipKeyHeaderService.create(this.layslipKeyHeader));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<LayslipKeyHeader>>) {
        result.subscribe((res: HttpResponse<LayslipKeyHeader>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: LayslipKeyHeader) {
        this.eventManager.broadcast({ name: 'layslipKeyHeaderListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackLayslipheaderById(index: number, item: Layslipheader) {
        return item.id;
    }

    trackLayslipRollDetailsById(index: number, item: LayslipRollDetails) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-layslip-key-header-popup',
    template: ''
})
export class LayslipKeyHeaderPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipKeyHeaderPopupService: LayslipKeyHeaderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.layslipKeyHeaderPopupService
                    .open(LayslipKeyHeaderDialogComponent as Component, params['id']);
            } else {
                this.layslipKeyHeaderPopupService
                    .open(LayslipKeyHeaderDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
