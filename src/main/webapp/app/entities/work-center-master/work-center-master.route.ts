import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { WorkCenterMasterComponent } from './work-center-master.component';
import { WorkCenterMasterDetailComponent } from './work-center-master-detail.component';
import { WorkCenterMasterPopupComponent } from './work-center-master-dialog.component';
import { WorkCenterMasterDeletePopupComponent } from './work-center-master-delete-dialog.component';

export const workCenterMasterRoute: Routes = [
    {
        path: 'work-center-master',
        component: WorkCenterMasterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCenterMasters'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'work-center-master/:id',
        component: WorkCenterMasterDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCenterMasters'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workCenterMasterPopupRoute: Routes = [
    {
        path: 'work-center-master-new',
        component: WorkCenterMasterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCenterMasters'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'work-center-master/:id/edit',
        component: WorkCenterMasterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCenterMasters'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'work-center-master/:id/delete',
        component: WorkCenterMasterDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCenterMasters'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
