import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { WorkCenterMaster } from './work-center-master.model';
import { WorkCenterMasterService } from './work-center-master.service';

@Component({
    selector: 'jhi-work-center-master-detail',
    templateUrl: './work-center-master-detail.component.html'
})
export class WorkCenterMasterDetailComponent implements OnInit, OnDestroy {

    workCenterMaster: WorkCenterMaster;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private workCenterMasterService: WorkCenterMasterService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWorkCenterMasters();
    }

    load(id) {
        this.workCenterMasterService.find(id)
            .subscribe((workCenterMasterResponse: HttpResponse<WorkCenterMaster>) => {
                this.workCenterMaster = workCenterMasterResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWorkCenterMasters() {
        this.eventSubscriber = this.eventManager.subscribe(
            'workCenterMasterListModification',
            (response) => this.load(this.workCenterMaster.id)
        );
    }
}
