import { Item } from "./item.interface";

export interface InvoiceBody{
    id?: number;
    item: number;
    quantity: number;
    percDiscount : number;
    totDiscount : number;
    items: Item;
}