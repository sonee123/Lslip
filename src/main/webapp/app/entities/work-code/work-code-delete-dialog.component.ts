import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { WorkCode } from './work-code.model';
import { WorkCodePopupService } from './work-code-popup.service';
import { WorkCodeService } from './work-code.service';

@Component({
    selector: 'jhi-work-code-delete-dialog',
    templateUrl: './work-code-delete-dialog.component.html'
})
export class WorkCodeDeleteDialogComponent {

    workCode: WorkCode;

    constructor(
        private workCodeService: WorkCodeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.workCodeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'workCodeListModification',
                content: 'Deleted an workCode'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-work-code-delete-popup',
    template: ''
})
export class WorkCodeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private workCodePopupService: WorkCodePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.workCodePopupService
                .open(WorkCodeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
