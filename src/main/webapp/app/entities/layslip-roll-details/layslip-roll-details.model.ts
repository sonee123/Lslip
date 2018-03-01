import { BaseEntity } from './../../shared';

export class LayslipRollDetails implements BaseEntity {
    constructor(
        public id?: number,
        public poNo?: string,
        public comMaterialCode?: string,
        public rollNumber?: string,
        public rollQty?: number,
        public grade?: string,
        public shade?: string,
        public flatStart?: number,
        public fittedStart?: number,
        public pillowStart?: number,
        public flatEnd?: number,
        public fittedEnd?: number,
        public pillowEnd?: number,
        public fullLength?: number,
        public halfLength?: number,
        public endbitPcs?: number,
        public estPillows?: number,
        public pillowProrata?: number,
        public rejectedFabric?: number,
    ) {
    }
}
