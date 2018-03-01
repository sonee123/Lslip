import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Layslipheader } from './layslipheader.model';
import { LayslipheaderService } from './layslipheader.service';

@Component({
    selector: 'jhi-layslipheader-detail',
    templateUrl: './layslipheader-detail.component.html'
})
export class LayslipheaderDetailComponent implements OnInit, OnDestroy {

    layslipheader: Layslipheader;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private layslipheaderService: LayslipheaderService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInLayslipheaders();
    }

    load(id) {
        this.layslipheaderService.find(id)
            .subscribe((layslipheaderResponse: HttpResponse<Layslipheader>) => {
                this.layslipheader = layslipheaderResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInLayslipheaders() {
        this.eventSubscriber = this.eventManager.subscribe(
            'layslipheaderListModification',
            (response) => this.load(this.layslipheader.id)
        );
    }
}
