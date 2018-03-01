import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LayslipKeyHeaderComponent } from './layslip-key-header.component';
import { LayslipKeyHeaderDetailComponent } from './layslip-key-header-detail.component';
import { LayslipKeyHeaderPopupComponent } from './layslip-key-header-dialog.component';
import { LayslipKeyHeaderDeletePopupComponent } from './layslip-key-header-delete-dialog.component';

export const layslipKeyHeaderRoute: Routes = [
    {
        path: 'layslip-key-header',
        component: LayslipKeyHeaderComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipKeyHeaders'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'layslip-key-header/:id',
        component: LayslipKeyHeaderDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipKeyHeaders'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const layslipKeyHeaderPopupRoute: Routes = [
    {
        path: 'layslip-key-header-new',
        component: LayslipKeyHeaderPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipKeyHeaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslip-key-header/:id/edit',
        component: LayslipKeyHeaderPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipKeyHeaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslip-key-header/:id/delete',
        component: LayslipKeyHeaderDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipKeyHeaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
