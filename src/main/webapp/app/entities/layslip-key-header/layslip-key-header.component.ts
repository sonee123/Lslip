import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LayslipKeyHeader } from './layslip-key-header.model';
import { LayslipKeyHeaderService } from './layslip-key-header.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-layslip-key-header',
    templateUrl: './layslip-key-header.component.html'
})
export class LayslipKeyHeaderComponent implements OnInit, OnDestroy {
layslipKeyHeaders: LayslipKeyHeader[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private layslipKeyHeaderService: LayslipKeyHeaderService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.layslipKeyHeaderService.query().subscribe(
            (res: HttpResponse<LayslipKeyHeader[]>) => {
                this.layslipKeyHeaders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLayslipKeyHeaders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LayslipKeyHeader) {
        return item.id;
    }
    registerChangeInLayslipKeyHeaders() {
        this.eventSubscriber = this.eventManager.subscribe('layslipKeyHeaderListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
