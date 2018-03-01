import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipRollDetails } from './layslip-roll-details.model';
import { LayslipRollDetailsPopupService } from './layslip-roll-details-popup.service';
import { LayslipRollDetailsService } from './layslip-roll-details.service';

@Component({
    selector: 'jhi-layslip-roll-details-dialog',
    templateUrl: './layslip-roll-details-dialog.component.html'
})
export class LayslipRollDetailsDialogComponent implements OnInit {

    layslipRollDetails: LayslipRollDetails;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private layslipRollDetailsService: LayslipRollDetailsService,
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
        if (this.layslipRollDetails.id !== undefined) {
            this.subscribeToSaveResponse(
                this.layslipRollDetailsService.update(this.layslipRollDetails));
        } else {
            this.subscribeToSaveResponse(
                this.layslipRollDetailsService.create(this.layslipRollDetails));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<LayslipRollDetails>>) {
        result.subscribe((res: HttpResponse<LayslipRollDetails>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: LayslipRollDetails) {
        this.eventManager.broadcast({ name: 'layslipRollDetailsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-layslip-roll-details-popup',
    template: ''
})
export class LayslipRollDetailsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipRollDetailsPopupService: LayslipRollDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.layslipRollDetailsPopupService
                    .open(LayslipRollDetailsDialogComponent as Component, params['id']);
            } else {
                this.layslipRollDetailsPopupService
                    .open(LayslipRollDetailsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
