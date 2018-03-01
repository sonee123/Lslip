import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LayslipheaderComponent } from './layslipheader.component';
import { LayslipheaderDetailComponent } from './layslipheader-detail.component';
import { LayslipheaderPopupComponent } from './layslipheader-dialog.component';
import { LayslipheaderDeletePopupComponent } from './layslipheader-delete-dialog.component';

export const layslipheaderRoute: Routes = [
    {
        path: 'layslipheader',
        component: LayslipheaderComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Layslipheaders'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'layslipheader/:id',
        component: LayslipheaderDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Layslipheaders'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const layslipheaderPopupRoute: Routes = [
    {
        path: 'layslipheader-new',
        component: LayslipheaderPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Layslipheaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslipheader/:id/edit',
        component: LayslipheaderPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Layslipheaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'layslipheader/:id/delete',
        component: LayslipheaderDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Layslipheaders'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
