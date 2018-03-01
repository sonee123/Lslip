import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WorkCode } from './work-code.model';
import { WorkCodeService } from './work-code.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-work-code',
    templateUrl: './work-code.component.html'
})
export class WorkCodeComponent implements OnInit, OnDestroy {
workCodes: WorkCode[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private workCodeService: WorkCodeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.workCodeService.query().subscribe(
            (res: HttpResponse<WorkCode[]>) => {
                this.workCodes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInWorkCodes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: WorkCode) {
        return item.id;
    }
    registerChangeInWorkCodes() {
        this.eventSubscriber = this.eventManager.subscribe('workCodeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
