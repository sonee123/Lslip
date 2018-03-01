import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { WorkCodeComponent } from './work-code.component';
import { WorkCodeDetailComponent } from './work-code-detail.component';
import { WorkCodePopupComponent } from './work-code-dialog.component';
import { WorkCodeDeletePopupComponent } from './work-code-delete-dialog.component';

export const workCodeRoute: Routes = [
    {
        path: 'work-code',
        component: WorkCodeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCodes'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'work-code/:id',
        component: WorkCodeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCodes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const workCodePopupRoute: Routes = [
    {
        path: 'work-code-new',
        component: WorkCodePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCodes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'work-code/:id/edit',
        component: WorkCodePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCodes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'work-code/:id/delete',
        component: WorkCodeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'WorkCodes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
