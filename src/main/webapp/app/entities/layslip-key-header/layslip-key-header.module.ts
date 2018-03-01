import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayslipSharedModule } from '../../shared';
import {
    LayslipKeyHeaderService,
    LayslipKeyHeaderPopupService,
    LayslipKeyHeaderComponent,
    LayslipKeyHeaderDetailComponent,
    LayslipKeyHeaderDialogComponent,
    LayslipKeyHeaderPopupComponent,
    LayslipKeyHeaderDeletePopupComponent,
    LayslipKeyHeaderDeleteDialogComponent,
    layslipKeyHeaderRoute,
    layslipKeyHeaderPopupRoute,
} from './';

const ENTITY_STATES = [
    ...layslipKeyHeaderRoute,
    ...layslipKeyHeaderPopupRoute,
];

@NgModule({
    imports: [
        LayslipSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LayslipKeyHeaderComponent,
        LayslipKeyHeaderDetailComponent,
        LayslipKeyHeaderDialogComponent,
        LayslipKeyHeaderDeleteDialogComponent,
        LayslipKeyHeaderPopupComponent,
        LayslipKeyHeaderDeletePopupComponent,
    ],
    entryComponents: [
        LayslipKeyHeaderComponent,
        LayslipKeyHeaderDialogComponent,
        LayslipKeyHeaderPopupComponent,
        LayslipKeyHeaderDeleteDialogComponent,
        LayslipKeyHeaderDeletePopupComponent,
    ],
    providers: [
        LayslipKeyHeaderService,
        LayslipKeyHeaderPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipLayslipKeyHeaderModule {}
