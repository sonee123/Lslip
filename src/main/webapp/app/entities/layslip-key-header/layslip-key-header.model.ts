import { BaseEntity } from './../../shared';

export class LayslipKeyHeader implements BaseEntity {
    constructor(
        public id?: number,
        public layslipheader?: BaseEntity,
        public layslipRoll?: BaseEntity,
    ) {
    }
}
