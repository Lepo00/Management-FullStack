import { UnitOfMeasure } from "./unit-of-measure.interface";

export interface InvoiceTail{
    id?: number;
    itemsValue : number;
    serviceValue : number;
    percDiscount : number;
    discountValue : number;
    totDiscount : number;
    taxable : number;
    totTax : number;
    netPay : number;
}