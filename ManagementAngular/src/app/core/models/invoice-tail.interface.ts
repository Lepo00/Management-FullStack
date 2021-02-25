import { UnitOfMeasure } from "./unit-of-measure.interface";

export interface InvoiceTail{
    id?: number;
    itemsValue? : number;
    serviceValue? : number;
    rowsDiscount?: number;
    discountPerc : number;
    discountValue : number;
    totDiscount? : number;
    taxable? : number;
    taxed? : number;
    finalAmount? : number;
}