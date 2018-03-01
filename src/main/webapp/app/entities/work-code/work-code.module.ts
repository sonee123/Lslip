import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayslipSharedModule } from '../../shared';
import { LayslipAdminModule } from '../../admin/admin.module';
import {
    WorkCodeService,
    WorkCodePopupService,
    WorkCodeComponent,
    WorkCodeDetailComponent,
    WorkCodeDialogComponent,
    WorkCodePopupComponent,
    WorkCodeDeletePopupComponent,
    WorkCodeDeleteDialogComponent,
    workCodeRoute,
    workCodePopupRoute,
} from './';

const ENTITY_STATES = [
    ...workCodeRoute,
    ...workCodePopupRoute,
];

@NgModule({
    imports: [
        LayslipSharedModule,
        LayslipAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        WorkCodeComponent,
        WorkCodeDetailComponent,
        WorkCodeDialogComponent,
        WorkCodeDeleteDialogComponent,
        WorkCodePopupComponent,
        WorkCodeDeletePopupComponent,
    ],
    entryComponents: [
        WorkCodeComponent,
        WorkCodeDialogComponent,
        WorkCodePopupComponent,
        WorkCodeDeleteDialogComponent,
        WorkCodeDeletePopupComponent,
    ],
    providers: [
        WorkCodeService,
        WorkCodePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipWorkCodeModule {}
