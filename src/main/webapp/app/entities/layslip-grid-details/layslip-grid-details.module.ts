import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayslipSharedModule } from '../../shared';
import {
    LayslipGridDetailsService,
    LayslipGridDetailsPopupService,
    LayslipGridDetailsComponent,
    LayslipGridDetailsDetailComponent,
    LayslipGridDetailsDialogComponent,
    LayslipGridDetailsPopupComponent,
    LayslipGridDetailsDeletePopupComponent,
    LayslipGridDetailsDeleteDialogComponent,
    layslipGridDetailsRoute,
    layslipGridDetailsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...layslipGridDetailsRoute,
    ...layslipGridDetailsPopupRoute,
];

@NgModule({
    imports: [
        LayslipSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LayslipGridDetailsComponent,
        LayslipGridDetailsDetailComponent,
        LayslipGridDetailsDialogComponent,
        LayslipGridDetailsDeleteDialogComponent,
        LayslipGridDetailsPopupComponent,
        LayslipGridDetailsDeletePopupComponent,
    ],
    entryComponents: [
        LayslipGridDetailsComponent,
        LayslipGridDetailsDialogComponent,
        LayslipGridDetailsPopupComponent,
        LayslipGridDetailsDeleteDialogComponent,
        LayslipGridDetailsDeletePopupComponent,
    ],
    providers: [
        LayslipGridDetailsService,
        LayslipGridDetailsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipLayslipGridDetailsModule {}
