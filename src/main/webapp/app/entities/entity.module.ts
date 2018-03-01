import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { LayslipLayslipheaderModule } from './layslipheader/layslipheader.module';
import { LayslipLayslipRollDetailsModule } from './layslip-roll-details/layslip-roll-details.module';
import { LayslipLayslipGridDetailsModule } from './layslip-grid-details/layslip-grid-details.module';
import { LayslipWorkCenterMasterModule } from './work-center-master/work-center-master.module';
import { LayslipWorkCodeModule } from './work-code/work-code.module';
import { LayslipLayslipKeyHeaderModule } from './layslip-key-header/layslip-key-header.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        LayslipLayslipheaderModule,
        LayslipLayslipRollDetailsModule,
        LayslipLayslipGridDetailsModule,
        LayslipWorkCenterMasterModule,
        LayslipWorkCodeModule,
        LayslipLayslipKeyHeaderModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayslipEntityModule {}
