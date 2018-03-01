import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipGridDetails } from './layslip-grid-details.model';
import { LayslipGridDetailsPopupService } from './layslip-grid-details-popup.service';
import { LayslipGridDetailsService } from './layslip-grid-details.service';

@Component({
    selector: 'jhi-layslip-grid-details-delete-dialog',
    templateUrl: './layslip-grid-details-delete-dialog.component.html'
})
export class LayslipGridDetailsDeleteDialogComponent {

    layslipGridDetails: LayslipGridDetails;

    constructor(
        private layslipGridDetailsService: LayslipGridDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.layslipGridDetailsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'layslipGridDetailsListModification',
                content: 'Deleted an layslipGridDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-layslip-grid-details-delete-popup',
    template: ''
})
export class LayslipGridDetailsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipGridDetailsPopupService: LayslipGridDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.layslipGridDetailsPopupService
                .open(LayslipGridDetailsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
