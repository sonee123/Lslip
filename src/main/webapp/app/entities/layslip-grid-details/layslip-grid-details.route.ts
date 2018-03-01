import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LayslipGridDetailsComponent } from './layslip-grid-details.component';
import { LayslipGridDetailsDetailComponent } from './layslip-grid-details-detail.component';
import { LayslipGridDetailsPopupComponent } from './layslip-grid-details-dialog.component';
import { LayslipGridDetailsDeletePopupComponent } from './layslip-grid-details-delete-dialog.component';

export const layslipGridDetailsRoute: Routes = [
    {
        path: 'layslip-grid-details',
        component: LayslipGridDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipGridDetails'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'layslip-grid-details/:id',
        component: LayslipGridDetailsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipGridDetails'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const layslipGridDetailsPopupRoute: Routes = [
    {
        path: 'layslip-grid-details-new',
        component: LayslipGridDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipGridDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslip-grid-details/:id/edit',
        component: LayslipGridDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipGridDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslip-grid-details/:id/delete',
        component: LayslipGridDetailsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipGridDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
