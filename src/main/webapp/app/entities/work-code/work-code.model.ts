import { BaseEntity, User } from './../../shared';

export class WorkCode implements BaseEntity {
    constructor(
        public id?: number,
        public user?: User,
        public wcCode1?: BaseEntity,
        public wcCode2?: BaseEntity,
        public wcCode3?: BaseEntity,
        public wcCode4?: BaseEntity,
        public wcCode5?: BaseEntity,
    ) {
    }
}
