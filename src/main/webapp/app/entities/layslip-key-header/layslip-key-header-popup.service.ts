import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { LayslipKeyHeader } from './layslip-key-header.model';
import { LayslipKeyHeaderService } from './layslip-key-header.service';

@Injectable()
export class LayslipKeyHeaderPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private layslipKeyHeaderService: LayslipKeyHeaderService

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
                this.layslipKeyHeaderService.find(id)
                    .subscribe((layslipKeyHeaderResponse: HttpResponse<LayslipKeyHeader>) => {
                        const layslipKeyHeader: LayslipKeyHeader = layslipKeyHeaderResponse.body;
                        this.ngbModalRef = this.layslipKeyHeaderModalRef(component, layslipKeyHeader);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.layslipKeyHeaderModalRef(component, new LayslipKeyHeader());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    layslipKeyHeaderModalRef(component: Component, layslipKeyHeader: LayslipKeyHeader): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.layslipKeyHeader = layslipKeyHeader;
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
