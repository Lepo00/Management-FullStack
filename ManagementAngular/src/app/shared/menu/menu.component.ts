import { Component, Input, OnInit } from '@angular/core';
import { PRIMARY_OUTLET, Router, UrlSegment, UrlSegmentGroup, UrlTree } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @Input()
  search:boolean;

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  logout(): void{
    sessionStorage.removeItem('user');
    this.router.navigateByUrl('/login');
  }

  searchButton(txt:string){
    //const tree: UrlTree = this.router.parseUrl(this.router.url);
    //const g: UrlSegmentGroup = tree.root.children[PRIMARY_OUTLET];
    //const s: UrlSegment[] = g.segments;
    //s[0].path;
    this.router.navigateByUrl("/"+this.router.url+"/"+txt);
  }

}
