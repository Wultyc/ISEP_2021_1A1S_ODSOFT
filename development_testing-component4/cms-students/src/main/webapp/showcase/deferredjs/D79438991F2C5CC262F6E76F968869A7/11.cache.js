$wnd.showcase.runAsyncCallback11("function hEb(){}\nfunction jEb(){}\nfunction cEb(a,b){a.b=b}\nfunction dEb(a){if(a==UDb){return true}ez();return a==XDb}\nfunction eEb(a){if(a==TDb){return true}ez();return a==SDb}\nfunction iEb(a){this.b=(NFb(),IFb).a;this.e=(SFb(),RFb).a;this.a=a}\nfunction aEb(a,b){var c;c=dC(a.fb,152);c.b=b.a;!!c.d&&Wyb(c.d,b)}\nfunction bEb(a,b){var c;c=dC(a.fb,152);c.e=b.a;!!c.d&&Yyb(c.d,b)}\nfunction fEb(){YDb();$yb.call(this);this.b=(NFb(),IFb);this.c=(SFb(),RFb);(Ovb(),this.e)[pac]=0;this.e[qac]=0}\nfunction YDb(){YDb=BX;RDb=new hEb;UDb=new hEb;TDb=new hEb;SDb=new hEb;VDb=new hEb;WDb=new hEb;XDb=new hEb}\nfunction ZDb(a,b,c){var d;if(c==RDb){if(b==a.a){return}else if(a.a){throw XW(new VWb('Only one CENTER widget may be added'))}}Rh(b);XPb(a.j,b);c==RDb&&(a.a=b);d=new iEb(c);b.fb=d;aEb(b,a.b);bEb(b,a.c);_Db(a);Th(b,a)}\nfunction $Db(a){var b,c,d,e,f,g,h;EPb((Ovb(),a.hb),'',_bc);g=new H2b;h=new fQb(a.j);while(h.b<h.c.c){b=dQb(h);f=dC(b.fb,152).a;d=dC(PZb(Z2b(g.d,f)),84);c=!d?1:d.a;e=f==VDb?'north'+c:f==WDb?'south'+c:f==XDb?'west'+c:f==SDb?'east'+c:f==UDb?'linestart'+c:f==TDb?'lineend'+c:T8b;EPb(Qo(b.hb),_bc,e);_Zb(g,f,gXb(c+1))}}\nfunction _Db(a){var b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;b=(Ovb(),a.d);while(qxb(b)>0){wo(b,pxb(b,0))}o=1;e=1;for(i=new fQb(a.j);i.b<i.c.c;){d=dQb(i);f=dC(d.fb,152).a;f==VDb||f==WDb?++o:(f==SDb||f==XDb||f==UDb||f==TDb)&&++e}p=mB(rR,m6b,262,o,0,1);for(g=0;g<o;++g){p[g]=new jEb;p[g].b=$doc.createElement(nac);so(b,Vvb(p[g].b))}k=0;l=e-1;m=0;q=o-1;c=null;for(h=new fQb(a.j);h.b<h.c.c;){d=dQb(h);j=dC(d.fb,152);r=$doc.createElement(oac);j.d=r;j.d[bac]=j.b;j.d.style[cac]=j.e;j.d[E6b]=j.f;j.d[D6b]=j.c;if(j.a==VDb){Rvb(p[m].b,r,p[m].a);so(r,Vvb(d.hb));r[gbc]=l-k+1;++m}else if(j.a==WDb){Rvb(p[q].b,r,p[q].a);so(r,Vvb(d.hb));r[gbc]=l-k+1;--q}else if(j.a==RDb){c=r}else if(dEb(j.a)){n=p[m];Rvb(n.b,r,n.a++);so(r,Vvb(d.hb));r[acc]=q-m+1;++k}else if(eEb(j.a)){n=p[m];Rvb(n.b,r,n.a);so(r,Vvb(d.hb));r[acc]=q-m+1;--l}}if(a.a){n=p[m];Rvb(n.b,c,n.a);so(c,Vvb(eh(a.a)))}}\nvar _bc='cwDockPanel';AX(416,1,S8b);_.Bc=function neb(){var a,b,c;QZ(this.a,(a=new fEb,(Ovb(),a.hb).className='cw-DockPanel',a.e[pac]=4,cEb(a,(NFb(),HFb)),ZDb(a,new ECb(Vbc),(YDb(),VDb)),ZDb(a,new ECb(Wbc),WDb),ZDb(a,new ECb(Xbc),SDb),ZDb(a,new ECb(Ybc),XDb),ZDb(a,new ECb(Zbc),VDb),ZDb(a,new ECb($bc),WDb),b=new ECb('\\u8FD9\\u4E2A\\u793A\\u4F8B\\u4E2D\\u5728<code>DockPanel<\\/code> \\u7684\\u4E2D\\u95F4\\u4F4D\\u7F6E\\u6709\\u4E00\\u4E2A<code>ScrollPanel<\\/code>\\u3002\\u5982\\u679C\\u5728\\u4E2D\\u95F4\\u653E\\u5165\\u5F88\\u591A\\u5185\\u5BB9\\uFF0C\\u5B83\\u5C31\\u4F1A\\u53D8\\u6210\\u9875\\u9762\\u5185\\u7684\\u53EF\\u6EDA\\u52A8\\u533A\\u57DF\\uFF0C\\u65E0\\u9700\\u4F7F\\u7528IFRAME\\u3002<br><br>\\u6B64\\u5904\\u4F7F\\u7528\\u4E86\\u76F8\\u5F53\\u591A\\u65E0\\u610F\\u4E49\\u7684\\u6587\\u5B57\\uFF0C\\u4E3B\\u8981\\u662F\\u4E3A\\u4E86\\u53EF\\u4EE5\\u6EDA\\u52A8\\u81F3\\u53EF\\u89C6\\u533A\\u57DF\\u7684\\u5E95\\u90E8\\u3002\\u5426\\u5219\\uFF0C\\u60A8\\u6050\\u6015\\u4E0D\\u5F97\\u4E0D\\u628A\\u5B83\\u7F29\\u5230\\u5F88\\u5C0F\\u624D\\u80FD\\u770B\\u5230\\u90A3\\u5C0F\\u5DE7\\u7684\\u6EDA\\u52A8\\u6761\\u3002'),c=new Zzb(b),c.hb.style[E6b]='400px',c.hb.style[D6b]='100px',ZDb(a,c,RDb),$Db(a),a))};AX(872,254,J6b,fEb);_.gc=function gEb(a){var b;b=Sxb(this,a);if(b){a==this.a&&(this.a=null);_Db(this)}return b};var RDb,SDb,TDb,UDb,VDb,WDb,XDb;var sR=zWb(H6b,'DockPanel',872);AX(151,1,{},hEb);var pR=zWb(H6b,'DockPanel/DockLayoutConstant',151);AX(152,1,{152:1},iEb);_.c='';_.f='';var qR=zWb(H6b,'DockPanel/LayoutData',152);AX(262,1,{262:1},jEb);_.a=0;var rR=zWb(H6b,'DockPanel/TmpRow',262);T5b(zl)(11);\n//# sourceURL=showcase-11.js\n")