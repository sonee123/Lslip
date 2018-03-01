import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipRollDetails } from './layslip-roll-details.model';
import { LayslipRollDetailsService } from './layslip-roll-details.service';

@Component({
    selector: 'jhi-layslip-roll-details-detail',
    templateUrl: './layslip-roll-details-detail.component.html'
})
export class LayslipRollDetailsDetailComponent implements OnInit, OnDestroy {

    layslipRollDetails: LayslipRollDetails;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private layslipRollDetailsService: LayslipRollDetailsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLayslipRollDetails();
    }

    load(id) {
        this.layslipRollDetailsService.find(id)
            .subscribe((layslipRollDetailsResponse: HttpResponse<LayslipRollDetails>) => {
                this.layslipRollDetails = layslipRollDetailsResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLayslipRollDetails() {
        this.eventSubscriber = this.eventManager.subscribe(
            'layslipRollDetailsListModification',
            (response) => this.load(this.layslipRollDetails.id)
        );
    }
}
