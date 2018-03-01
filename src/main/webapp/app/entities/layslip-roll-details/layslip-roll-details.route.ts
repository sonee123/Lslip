import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LayslipRollDetailsComponent } from './layslip-roll-details.component';
import { LayslipRollDetailsDetailComponent } from './layslip-roll-details-detail.component';
import { LayslipRollDetailsPopupComponent } from './layslip-roll-details-dialog.component';
import { LayslipRollDetailsDeletePopupComponent } from './layslip-roll-details-delete-dialog.component';

export const layslipRollDetailsRoute: Routes = [
    {
        path: 'layslip-roll-details',
        component: LayslipRollDetailsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipRollDetails'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'layslip-roll-details/:id',
        component: LayslipRollDetailsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipRollDetails'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const layslipRollDetailsPopupRoute: Routes = [
    {
        path: 'layslip-roll-details-new',
        component: LayslipRollDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipRollDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslip-roll-details/:id/edit',
        component: LayslipRollDetailsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipRollDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslip-roll-details/:id/delete',
        component: LayslipRollDetailsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LayslipRollDetails'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
