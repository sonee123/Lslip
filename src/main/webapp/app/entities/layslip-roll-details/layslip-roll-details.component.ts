import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { LayslipRollDetails } from './layslip-roll-details.model';
import { LayslipRollDetailsService } from './layslip-roll-details.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-layslip-roll-details',
    templateUrl: './layslip-roll-details.component.html'
})
export class LayslipRollDetailsComponent implements OnInit, OnDestroy {
layslipRollDetails: LayslipRollDetails[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private layslipRollDetailsService: LayslipRollDetailsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.layslipRollDetailsService.query().subscribe(
            (res: HttpResponse<LayslipRollDetails[]>) => {
                this.layslipRollDetails = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLayslipRollDetails();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: LayslipRollDetails) {
        return item.id;
    }
    registerChangeInLayslipRollDetails() {
        this.eventSubscriber = this.eventManager.subscribe('layslipRollDetailsListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
