import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Layslipheader } from './layslipheader.model';
import { LayslipheaderService } from './layslipheader.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-layslipheader',
    templateUrl: './layslipheader.component.html'
})
export class LayslipheaderComponent implements OnInit, OnDestroy {
layslipheaders: Layslipheader[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private layslipheaderService: LayslipheaderService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.layslipheaderService.query().subscribe(
            (res: HttpResponse<Layslipheader[]>) => {
                this.layslipheaders = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInLayslipheaders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Layslipheader) {
        return item.id;
    }
    registerChangeInLayslipheaders() {
        this.eventSubscriber = this.eventManager.subscribe('layslipheaderListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
