import { BaseEntity } from './../../shared';

export class Layslipheader implements BaseEntity {
    constructor(
        public id?: number,
        public priority?: number,
        public poNo?: string,
        public materialNo?: string,
        public materialDesc?: string,
        public mainGrid?: string,
        public orderQty?: number,
        public remainingQty?: number,
        public plannedQty?: number,
        public layComponent?: string,
        public flat?: number,
        public fitted?: number,
        public pillow?: number,
        public flatMatCode?: string,
        public fittedMatCode?: string,
        public pillowMatCode?: string,
        public pillowGrid?: string,
        public flatToBeMade?: string,
        public fittedToBeMade?: string,
        public pillowToBeMade?: string,
        public flatWays?: number,
        public fittedWays?: number,
        public pillowWays?: number,
        public flatPiecesPerWay?: number,
        public fittedPiecesPerWay?: number,
        public pillowPiecesPerWay?: number,
    ) {
    }
}
