import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LayslipSharedModule } from '../../shared';
import {
    LayslipRollDetailsService,
    LayslipRollDetailsPopupService,
    LayslipRollDetailsComponent,
    LayslipRollDetailsDetailComponent,
    LayslipRollDetailsDialogComponent,
    LayslipRollDetailsPopupComponent,
    LayslipRollDetailsDeletePopupComponent,
    LayslipRollDetailsDeleteDialogComponent,
    layslipRollDetailsRoute,
    layslipRollDetailsPopupRoute,
} from './';

const ENTITY_STATES = [
    ...layslipRollDetailsRoute,
    ...layslipRollDetailsPopupRoute,
];

@NgModule({
    imports: [
        LayslipSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LayslipRollDetailsComponent,
        LayslipRollDetailsDetailComponent,
        LayslipRollDetailsDialogComponent,
        LayslipRollDetailsDeleteDialogComponent,
        LayslipRollDetailsPopupComponent,
        LayslipRollDetailsDeletePopupComponent,
    ],
    entryComponents: [
        LayslipRollDetailsComponent,
        LayslipRollDetailsDialogComponent,
        LayslipRollDetailsPopupComponent,
        LayslipRollDetailsDeleteDialogComponent,
        LayslipRollDetailsDeletePopupComponent,
    ],
    providers: [
        LayslipRollDetailsService,
        LayslipRollDetailsPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipLayslipRollDetailsModule {}
