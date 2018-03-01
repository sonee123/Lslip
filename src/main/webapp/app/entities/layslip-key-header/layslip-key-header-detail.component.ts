import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { LayslipKeyHeader } from './layslip-key-header.model';
import { LayslipKeyHeaderService } from './layslip-key-header.service';

@Component({
    selector: 'jhi-layslip-key-header-detail',
    templateUrl: './layslip-key-header-detail.component.html'
})
export class LayslipKeyHeaderDetailComponent implements OnInit, OnDestroy {

    layslipKeyHeader: LayslipKeyHeader;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private layslipKeyHeaderService: LayslipKeyHeaderService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLayslipKeyHeaders();
    }

    load(id) {
        this.layslipKeyHeaderService.find(id)
            .subscribe((layslipKeyHeaderResponse: HttpResponse<LayslipKeyHeader>) => {
                this.layslipKeyHeader = layslipKeyHeaderResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLayslipKeyHeaders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'layslipKeyHeaderListModification',
            (response) => this.load(this.layslipKeyHeader.id)
        );
    }
}
