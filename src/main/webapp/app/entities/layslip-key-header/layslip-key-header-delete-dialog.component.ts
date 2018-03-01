import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipKeyHeader } from './layslip-key-header.model';
import { LayslipKeyHeaderPopupService } from './layslip-key-header-popup.service';
import { LayslipKeyHeaderService } from './layslip-key-header.service';

@Component({
    selector: 'jhi-layslip-key-header-delete-dialog',
    templateUrl: './layslip-key-header-delete-dialog.component.html'
})
export class LayslipKeyHeaderDeleteDialogComponent {

    layslipKeyHeader: LayslipKeyHeader;

    constructor(
        private layslipKeyHeaderService: LayslipKeyHeaderService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.layslipKeyHeaderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'layslipKeyHeaderListModification',
                content: 'Deleted an layslipKeyHeader'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-layslip-key-header-delete-popup',
    template: ''
})
export class LayslipKeyHeaderDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipKeyHeaderPopupService: LayslipKeyHeaderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.layslipKeyHeaderPopupService
                .open(LayslipKeyHeaderDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
