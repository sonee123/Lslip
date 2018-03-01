import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { LayslipGridDetails } from './layslip-grid-details.model';
import { LayslipGridDetailsService } from './layslip-grid-details.service';

@Injectable()
export class LayslipGridDetailsPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private layslipGridDetailsService: LayslipGridDetailsService

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
                this.layslipGridDetailsService.find(id)
                    .subscribe((layslipGridDetailsResponse: HttpResponse<LayslipGridDetails>) => {
                        const layslipGridDetails: LayslipGridDetails = layslipGridDetailsResponse.body;
                        this.ngbModalRef = this.layslipGridDetailsModalRef(component, layslipGridDetails);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.layslipGridDetailsModalRef(component, new LayslipGridDetails());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    layslipGridDetailsModalRef(component: Component, layslipGridDetails: LayslipGridDetails): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.layslipGridDetails = layslipGridDetails;
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
