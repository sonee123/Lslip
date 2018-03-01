import { BaseEntity } from './../../shared';

export class WorkCenterMaster implements BaseEntity {
    constructor(
        public id?: number,
        public title?: string,
        public layslips?: BaseEntity[],
    ) {
    }
}
