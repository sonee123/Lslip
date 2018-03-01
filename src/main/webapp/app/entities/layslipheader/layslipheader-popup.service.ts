import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Layslipheader } from './layslipheader.model';
import { LayslipheaderService } from './layslipheader.service';

@Injectable()
export class LayslipheaderPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private layslipheaderService: LayslipheaderService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.layslipheaderService.find(id)
                    .subscribe((layslipheaderResponse: HttpResponse<Layslipheader>) => {
                        const layslipheader: Layslipheader = layslipheaderResponse.body;
                        this.ngbModalRef = this.layslipheaderModalRef(component, layslipheader);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.layslipheaderModalRef(component, new Layslipheader());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    layslipheaderModalRef(component: Component, layslipheader: Layslipheader): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.layslipheader = layslipheader;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
