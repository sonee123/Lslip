import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WorkCenterMaster } from './work-center-master.model';
import { WorkCenterMasterPopupService } from './work-center-master-popup.service';
import { WorkCenterMasterService } from './work-center-master.service';

@Component({
    selector: 'jhi-work-center-master-delete-dialog',
    templateUrl: './work-center-master-delete-dialog.component.html'
})
export class WorkCenterMasterDeleteDialogComponent {

    workCenterMaster: WorkCenterMaster;

    constructor(
        private workCenterMasterService: WorkCenterMasterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workCenterMasterService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'workCenterMasterListModification',
                content: 'Deleted an workCenterMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-work-center-master-delete-popup',
    template: ''
})
export class WorkCenterMasterDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workCenterMasterPopupService: WorkCenterMasterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.workCenterMasterPopupService
                .open(WorkCenterMasterDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
