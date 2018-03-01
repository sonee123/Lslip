import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { WorkCode } from './work-code.model';
import { WorkCodeService } from './work-code.service';

@Component({
    selector: 'jhi-work-code-detail',
    templateUrl: './work-code-detail.component.html'
})
export class WorkCodeDetailComponent implements OnInit, OnDestroy {

    workCode: WorkCode;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private workCodeService: WorkCodeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWorkCodes();
    }

    load(id) {
        this.workCodeService.find(id)
            .subscribe((workCodeResponse: HttpResponse<WorkCode>) => {
                this.workCode = workCodeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWorkCodes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'workCodeListModification',
            (response) => this.load(this.workCode.id)
        );
    }
}
