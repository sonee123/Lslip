import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Layslipheader } from './layslipheader.model';
import { LayslipheaderPopupService } from './layslipheader-popup.service';
import { LayslipheaderService } from './layslipheader.service';

@Component({
    selector: 'jhi-layslipheader-delete-dialog',
    templateUrl: './layslipheader-delete-dialog.component.html'
})
export class LayslipheaderDeleteDialogComponent {

    layslipheader: Layslipheader;

    constructor(
        private layslipheaderService: LayslipheaderService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.layslipheaderService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'layslipheaderListModification',
                content: 'Deleted an layslipheader'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-layslipheader-delete-popup',
    template: ''
})
export class LayslipheaderDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private layslipheaderPopupService: LayslipheaderPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.layslipheaderPopupService
                .open(LayslipheaderDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
