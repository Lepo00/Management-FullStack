import { Customer } from "./customer.interface";
import { InvoiceMaster } from "./invoice-master.interface";

export interface User {
    id?: number;
    ivaCode: string;
    address: string;
    phone: string;
    username: string;
    password: string;
    invoices: InvoiceMaster[];
    customers: Customer[];
}
