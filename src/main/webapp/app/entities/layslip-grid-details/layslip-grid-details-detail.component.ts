import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipGridDetails } from './layslip-grid-details.model';
import { LayslipGridDetailsService } from './layslip-grid-details.service';

@Component({
    selector: 'jhi-layslip-grid-details-detail',
    templateUrl: './layslip-grid-details-detail.component.html'
})
export class LayslipGridDetailsDetailComponent implements OnInit, OnDestroy {

    layslipGridDetails: LayslipGridDetails;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private layslipGridDetailsService: LayslipGridDetailsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLayslipGridDetails();
    }

    load(id) {
        this.layslipGridDetailsService.find(id)
            .subscribe((layslipGridDetailsResponse: HttpResponse<LayslipGridDetails>) => {
                this.layslipGridDetails = layslipGridDetailsResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLayslipGridDetails() {
        this.eventSubscriber = this.eventManager.subscribe(
            'layslipGridDetailsListModification',
            (response) => this.load(this.layslipGridDetails.id)
        );
    }
}
