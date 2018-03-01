import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipRollDetails } from './layslip-roll-details.model';
import { LayslipRollDetailsPopupService } from './layslip-roll-details-popup.service';
import { LayslipRollDetailsService } from './layslip-roll-details.service';

@Component({
    selector: 'jhi-layslip-roll-details-delete-dialog',
    templateUrl: './layslip-roll-details-delete-dialog.component.html'
})
export class LayslipRollDetailsDeleteDialogComponent {

    layslipRollDetails: LayslipRollDetails;

    constructor(
        private layslipRollDetailsService: LayslipRollDetailsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.layslipRollDetailsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'layslipRollDetailsListModification',
                content: 'Deleted an layslipRollDetails'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-layslip-roll-details-delete-popup',
    template: ''
})
export class LayslipRollDetailsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipRollDetailsPopupService: LayslipRollDetailsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.layslipRollDetailsPopupService
                .open(LayslipRollDetailsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
