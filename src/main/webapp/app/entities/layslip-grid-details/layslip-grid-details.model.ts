import { BaseEntity } from './../../shared';

export class LayslipGridDetails implements BaseEntity {
    constructor(
        public id?: number,
        public laySlipNo?: string,
        public subLayslipNo?: string,
        public mainGrid?: string,
        public pillows?: number,
        public wastFabric?: number,
    ) {
    }
}
