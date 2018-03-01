import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayslipSharedModule } from '../../shared';
import {
    LayslipheaderService,
    LayslipheaderPopupService,
    LayslipheaderComponent,
    LayslipheaderDetailComponent,
    LayslipheaderDialogComponent,
    LayslipheaderPopupComponent,
    LayslipheaderDeletePopupComponent,
    LayslipheaderDeleteDialogComponent,
    layslipheaderRoute,
    layslipheaderPopupRoute,
} from './';

const ENTITY_STATES = [
    ...layslipheaderRoute,
    ...layslipheaderPopupRoute,
];

@NgModule({
    imports: [
        LayslipSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LayslipheaderComponent,
        LayslipheaderDetailComponent,
        LayslipheaderDialogComponent,
        LayslipheaderDeleteDialogComponent,
        LayslipheaderPopupComponent,
        LayslipheaderDeletePopupComponent,
    ],
    entryComponents: [
        LayslipheaderComponent,
        LayslipheaderDialogComponent,
        LayslipheaderPopupComponent,
        LayslipheaderDeleteDialogComponent,
        LayslipheaderDeletePopupComponent,
    ],
    providers: [
        LayslipheaderService,
        LayslipheaderPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipLayslipheaderModule {}
