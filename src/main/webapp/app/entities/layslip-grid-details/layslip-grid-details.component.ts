import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LayslipGridDetails } from './layslip-grid-details.model';
import { LayslipGridDetailsService } from './layslip-grid-details.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-layslip-grid-details',
    templateUrl: './layslip-grid-details.component.html'
})
export class LayslipGridDetailsComponent implements OnInit, OnDestroy {
layslipGridDetails: LayslipGridDetails[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private layslipGridDetailsService: LayslipGridDetailsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.layslipGridDetailsService.query().subscribe(
            (res: HttpResponse<LayslipGridDetails[]>) => {
                this.layslipGridDetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLayslipGridDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LayslipGridDetails) {
        return item.id;
    }
    registerChangeInLayslipGridDetails() {
        this.eventSubscriber = this.eventManager.subscribe('layslipGridDetailsListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
