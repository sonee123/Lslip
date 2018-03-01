import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipGridDetails } from './layslip-grid-details.model';
import { LayslipGridDetailsPopupService } from './layslip-grid-details-popup.service';
import { LayslipGridDetailsService } from './layslip-grid-details.service';

@Component({
    selector: 'jhi-layslip-grid-details-dialog',
    templateUrl: './layslip-grid-details-dialog.component.html'
})
export class LayslipGridDetailsDialogComponent implements OnInit {

    layslipGridDetails: LayslipGridDetails;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private layslipGridDetailsService: LayslipGridDetailsService,
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
        if (this.layslipGridDetails.id !== undefined) {
            this.subscribeToSaveResponse(
                this.layslipGridDetailsService.update(this.layslipGridDetails));
        } else {
            this.subscribeToSaveResponse(
                this.layslipGridDetailsService.create(this.layslipGridDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<LayslipGridDetails>>) {
        result.subscribe((res: HttpResponse<LayslipGridDetails>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: LayslipGridDetails) {
        this.eventManager.broadcast({ name: 'layslipGridDetailsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-layslip-grid-details-popup',
    template: ''
})
export class LayslipGridDetailsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipGridDetailsPopupService: LayslipGridDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.layslipGridDetailsPopupService
                    .open(LayslipGridDetailsDialogComponent as Component, params['id']);
            } else {
                this.layslipGridDetailsPopupService
                    .open(LayslipGridDetailsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
