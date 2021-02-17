import { InvoiceBody } from "./invoice-body.interface";
import { InvoiceTail } from "./invoice-tail.interface";

export interface InvoiceMaster{
    id?: number;
    number:number;
    accountholder: string;
    date: Date;
    paymentMethod: string;
    rows: InvoiceBody[];
    tail: InvoiceTail;
}