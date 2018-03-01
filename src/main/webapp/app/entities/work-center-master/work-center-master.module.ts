import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayslipSharedModule } from '../../shared';
import {
    WorkCenterMasterService,
    WorkCenterMasterPopupService,
    WorkCenterMasterComponent,
    WorkCenterMasterDetailComponent,
    WorkCenterMasterDialogComponent,
    WorkCenterMasterPopupComponent,
    WorkCenterMasterDeletePopupComponent,
    WorkCenterMasterDeleteDialogComponent,
    workCenterMasterRoute,
    workCenterMasterPopupRoute,
} from './';

const ENTITY_STATES = [
    ...workCenterMasterRoute,
    ...workCenterMasterPopupRoute,
];

@NgModule({
    imports: [
        LayslipSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        WorkCenterMasterComponent,
        WorkCenterMasterDetailComponent,
        WorkCenterMasterDialogComponent,
        WorkCenterMasterDeleteDialogComponent,
        WorkCenterMasterPopupComponent,
        WorkCenterMasterDeletePopupComponent,
    ],
    entryComponents: [
        WorkCenterMasterComponent,
        WorkCenterMasterDialogComponent,
        WorkCenterMasterPopupComponent,
        WorkCenterMasterDeleteDialogComponent,
        WorkCenterMasterDeletePopupComponent,
    ],
    providers: [
        WorkCenterMasterService,
        WorkCenterMasterPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipWorkCenterMasterModule {}
