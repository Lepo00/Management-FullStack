import { Item } from "./item.interface";

export interface InvoiceBody{
    id?: number;
    item: Item;
    quantity: number;
    percDiscount : number;
    totDiscount : number;
    netPrice:number;
    taxable:number;
    taxed:number;
    finalAmount:number;
}