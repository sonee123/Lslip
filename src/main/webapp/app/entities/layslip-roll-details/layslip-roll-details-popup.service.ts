import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { LayslipRollDetails } from './layslip-roll-details.model';
import { LayslipRollDetailsService } from './layslip-roll-details.service';

@Injectable()
export class LayslipRollDetailsPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private layslipRollDetailsService: LayslipRollDetailsService

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
                this.layslipRollDetailsService.find(id)
                    .subscribe((layslipRollDetailsResponse: HttpResponse<LayslipRollDetails>) => {
                        const layslipRollDetails: LayslipRollDetails = layslipRollDetailsResponse.body;
                        this.ngbModalRef = this.layslipRollDetailsModalRef(component, layslipRollDetails);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.layslipRollDetailsModalRef(component, new LayslipRollDetails());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    layslipRollDetailsModalRef(component: Component, layslipRollDetails: LayslipRollDetails): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.layslipRollDetails = layslipRollDetails;
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
