import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WorkCenterMaster } from './work-center-master.model';
import { WorkCenterMasterService } from './work-center-master.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-work-center-master',
    templateUrl: './work-center-master.component.html'
})
export class WorkCenterMasterComponent implements OnInit, OnDestroy {
workCenterMasters: WorkCenterMaster[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private workCenterMasterService: WorkCenterMasterService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.workCenterMasterService.query().subscribe(
            (res: HttpResponse<WorkCenterMaster[]>) => {
                this.workCenterMasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInWorkCenterMasters();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: WorkCenterMaster) {
        return item.id;
    }
    registerChangeInWorkCenterMasters() {
        this.eventSubscriber = this.eventManager.subscribe('workCenterMasterListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
