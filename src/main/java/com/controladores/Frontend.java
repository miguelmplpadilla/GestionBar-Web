package com.controladores;

import com.TablasModificadas.*;
import com.dao.*;
import com.entity.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class Frontend {

    @Autowired
    private BarDao barDao;

    @Autowired
    private BarProveedorDao barProveedorDao;

    @Autowired
    private ProveedoresDao proveedoresDao;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private CategoriasDao categoriasDao;

    @Autowired
    private ProductoProveedorDao productoProveedorDao;

    @Autowired
    private ComparativaPrecioDao comparativaPrecioDao;

    @Autowired
    private CodigosTrazabilidadDao codigosTrazabilidadDao;

    @Autowired
    private DatosbarDao datosbarDao;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping({"/", "", "/home", "/inicio", "/index", "/graficoPrecios"})
    public String graficoPrecios(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        if (datosbarDao.getDatosByBarId(bar.getId()).size() == 0) {
            Datosbar datosbar = new Datosbar();
            datosbar.setFkBar(bar.getId());
            datosbarDao.create(datosbar);
        }

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        List<Productos> productosList = productoDao.getAll();

        List<Comparativa> comparativas = new ArrayList<>();

        if (productosList.size() != 0) {
            int num = 0;
            for (int i = 0; i < productosList.size(); i++) {
                num++;
                String nombreProducto = productosList.get(i).getNombre();
                List<Comparativaprecio> comparativarecios = comparativaPrecioDao.getByProductId(productosList.get(i).getId(), bar.getId());

                if (comparativarecios.size() != 0) {
                    String fechas = " ,";
                    String precios = " ,";

                    for (int j = 0; j < comparativarecios.size(); j++) {
                        if (j != comparativarecios.size() - 1) {
                            fechas = fechas + comparativarecios.get(j).getFecha().toString()+" - "+proveedoresDao.getById(comparativarecios.get(j).getFkProveedor()).get(0).getNombre()+ ",";
                            precios = precios + comparativarecios.get(j).getPrecio() + ",";
                        } else {
                            fechas = fechas + comparativarecios.get(j).getFecha().toString()+" - "+proveedoresDao.getById(comparativarecios.get(j).getFkProveedor()).get(0).getNombre();
                            precios = precios + comparativarecios.get(j).getPrecio() + "," + comparativarecios.get(j).getPrecio() + 1;
                        }
                    }

                    comparativas.add(new Comparativa(num, nombreProducto, new PorductoComparativa(fechas, precios)));
                }
            }

            model.addAttribute("datos", comparativas);

            List<ProveedorComparativaPrecio> proveedorComparativaPrecios = new ArrayList<>();
            List<Proveedores> proveedores = proveedoresDao.getAll();

            if (proveedores.size() != 0) {
                for (int n = 0; n < proveedores.size(); n++) {
                    boolean f = false;
                    List<Comparativa> comparativasPorProveedor = new ArrayList<>();
                    num = 0;
                    for (int i = 0; i < productosList.size(); i++) {
                        num++;
                        String nombreProducto = productosList.get(i).getNombre();
                        List<Comparativaprecio> comparativarecios = comparativaPrecioDao.getByProveedorIdAndProductoId(proveedores.get(n).getId(), productosList.get(i).getId(), bar.getId());

                        if (comparativarecios.size() != 0) {
                            f = true;
                            String fechas = " ,";
                            String precios = " ,";

                            for (int j = 0; j < comparativarecios.size(); j++) {
                                if (j != comparativarecios.size() - 1) {
                                    fechas = fechas + comparativarecios.get(j).getFecha().toString() + ",";
                                    precios = precios + comparativarecios.get(j).getPrecio() + ",";
                                } else {
                                    fechas = fechas + comparativarecios.get(j).getFecha().toString();
                                    precios = precios + comparativarecios.get(j).getPrecio() + "," + comparativarecios.get(j).getPrecio() + 1;
                                }
                            }

                            comparativasPorProveedor.add(new Comparativa(num, nombreProducto, new PorductoComparativa(fechas, precios)));
                        }
                    }

                    if (f) {
                        proveedorComparativaPrecios.add(new ProveedorComparativaPrecio(proveedores.get(n), comparativasPorProveedor));
                    }

                }
                model.addAttribute("datos2", proveedorComparativaPrecios);
            }
        }


        return "ComparativaPrecio/graficoPrecios";
    }

    @GetMapping({"/introducir-producto"})
    public String introducirProducto(@RequestParam(value = "nombre", required = false) String nombre,
                                     @RequestParam(value = "codigoTrazabilidad", required = false) String codigoTrazabilidad,
                                     @RequestParam(value = "categoria", required = false) String categoria,
                                     @RequestParam(value = "ingredientes", required = false) String ingredientes,
                                     @RequestParam(value = "alergenos", required = false) String alergenos,
                                     @RequestParam(value = "trazas", required = false) String trazas,
                                     @RequestParam(value = "img", required = false) String img,
                                     @RequestParam(value = "code", required = false) String code,
                                     Model model) throws IOException, JSONException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        model.addAttribute("img", "https://www.compuserviciosbcs.com/images/items/no-img.jpg");
        if (img == null) {
            img = "https://www.compuserviciosbcs.com/images/items/no-img.jpg";
        }

        if (code != null) {
            if (!code.equals("")) {
                URLConnection connection = new URL("https://world.openfoodfacts.org/api/v2/search?code=" + code + "&fields=code,product_name,image_front_url,ingredients_text_es,ingredients_text_with_allergens_es").openConnection();

                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String inputLine;
                StringBuilder builder = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    builder.append(inputLine);
                    builder.append(" ");
                }

                String html = builder.toString();

                Document doc = Jsoup.parse(html);

                String jsonText = doc.body().text();

                JSONObject obj = new JSONObject(jsonText);

                System.out.println(jsonText);

                JSONArray arr = obj.getJSONArray("products");

                nombre = arr.getJSONObject(0).getString("product_name");

                try {
                    ingredientes = arr.getJSONObject(0).getString("ingredients_text_es");
                } catch (Exception e) {
                }

                try {
                    alergenos = arr.getJSONObject(0).getString("ingredients_text_with_allergens_es");
                } catch (Exception e) {
                }

                img = arr.getJSONObject(0).getString("image_front_url");
                codigoTrazabilidad = arr.getJSONObject(0).getString("code");

            }
        }

        model.addAttribute("name", nombre);
        model.addAttribute("cod", codigoTrazabilidad);

        model.addAttribute("ingredients", ingredientes);
        model.addAttribute("al", alergenos);
        model.addAttribute("img", img);

        model.addAttribute("categorias", categoriasDao.getAll());

        model.addAttribute("introducir", "Introduce producto");

        model.addAttribute("isGood", true);
        model.addAttribute("cat", "...");

        if (nombre != null && categoria != null) {
            if (!nombre.replace(" ", "").equals("") && !categoria.replace("...", "").equals("")) {
                model.addAttribute("introducir", "Producto introducido correctamente");
                model.addAttribute("isGood", true);

                Productos producto = new Productos();

                producto.setNombre(nombre);
                producto.setFkCategoria(productoDao.getIdByNombre(categoria).get(0).getId());
                producto.setCodigoTrazabilidad(codigoTrazabilidad);

                if (img != null) {
                    if (img.equals("")) {
                        producto.setImg("https://www.compuserviciosbcs.com/images/items/no-img.jpg");
                    } else {
                        System.out.println(img);
                        producto.setImg(img);
                    }
                }

                if (ingredientes != null) {
                    producto.setIngredientes(ingredientes);
                }
                if (alergenos != null) {
                    producto.setAlergenos(alergenos);
                }
                if (trazas != null) {
                    producto.setTrazas(trazas);
                }

                List<Productos> allProductos = productoDao.getAll();

                boolean f = true;

                for (int i = 0; i < allProductos.size(); i++) {
                    if (allProductos.get(i).getNombre() == producto.getNombre()
                            && allProductos.get(i).getImg() == producto.getImg()
                            && allProductos.get(i).getCodigoTrazabilidad() == producto.getCodigoTrazabilidad()
                            && allProductos.get(i).getIngredientes() == producto.getIngredientes()
                            && allProductos.get(i).getAlergenos() == producto.getAlergenos()) {
                        f = false;
                        break;
                    }
                }

                if (f) {
                    productoDao.create(producto);
                }

                model.addAttribute("name", "");
                model.addAttribute("cod", "");

                model.addAttribute("ingredients", "");
                model.addAttribute("al", "");
                model.addAttribute("img", "https://www.compuserviciosbcs.com/images/items/no-img.jpg");
            } else {
                model.addAttribute("isGood", false);
            }
        }

        return "introducir-producto";
    }

    @GetMapping({"/productos"})
    public String productos(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        model.addAttribute("tipo", "Producto");

        model.addAttribute("datos", productoDao.getAll());

        return "productos";
    }

    @GetMapping({"/escanerProducto"})
    public String barcode(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        model.addAttribute("tipo", "Productos");

        return "escanerProducto";
    }

    @GetMapping({"/detalleProductos"})
    public String detalleProductos(@RequestParam(value = "id", defaultValue = "-1", required = false) int id,
                                   @RequestParam(value = "nombre", required = false) String nombre,
                                   @RequestParam(value = "categoria", required = false) String categoria,
                                   @RequestParam(value = "ingredientes", required = false) String ingredientes,
                                   @RequestParam(value = "alergenos", required = false) String alergenos,
                                   @RequestParam(value = "trazas", required = false) String trazas,
                                   @RequestParam(value = "img", required = false) String img,
                                   @RequestParam(value = "code", required = false) String code,
                                   Model model) throws IOException, JSONException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());
        model.addAttribute("isGood", true);

        model.addAttribute("cat", "...");
        model.addAttribute("id", id);
        model.addAttribute("img", "https://www.compuserviciosbcs.com/images/items/no-img.jpg");

        if (id != -1) {
            if (productoDao.getById(id) != null) {

                Productos p = productoDao.getById(id).get(0);

                if (nombre != null) {
                    if (!nombre.equals(p.getNombre()) || !categoria.equals(p.getFkCategoria()) || !ingredientes.equals(p.getIngredientes()) || !alergenos.equals(p.getAlergenos()) || !trazas.equals(p.getTrazas()) || !img.equals(p.getImg())) {
                        if (!nombre.equals("") && !categoria.equals("")) {
                            p.setNombre(nombre);
                            p.setFkCategoria(productoDao.getIdByNombre(categoria).get(0).getId());
                            p.setIngredientes(ingredientes);
                            p.setAlergenos(alergenos);
                            p.setTrazas(trazas);
                            p.setImg(img);

                            productoDao.update(p);
                        } else {
                            model.addAttribute("isGood", false);
                        }
                    }
                }

                System.out.println(p.getNombre());

                model.addAttribute("name", p.getNombre());
                model.addAttribute("cod", p.getCodigoTrazabilidad());

                model.addAttribute("ingredients", p.getIngredientes());
                model.addAttribute("al", p.getAlergenos());
                model.addAttribute("img", p.getImg());

                model.addAttribute("cat", categoriasDao.findCategoriaById(p.getFkCategoria()).toString().replace("[", "").replace("]", ""));
            }
        }


        model.addAttribute("categorias", categoriasDao.getAll());

        return "Producto/DetalleProducto";
    }

    @GetMapping({"/proveedores"})
    public String proveedores(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        model.addAttribute("tipo", "Proveedores");

        model.addAttribute("datos", proveedoresDao.getAll());

        return "Proveedores/proveedores";
    }

    @GetMapping({"/introducirProveedor"})
    public String introducirProveedor(@RequestParam(value = "nombre", required = false) String nombre,
                                      @RequestParam(value = "numero", required = false) String numero,
                                      Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());
        model.addAttribute("isGood", true);

        if (nombre != null && numero != null) {
            if (!nombre.equals("") && !numero.equals("")) {
                Proveedores proveedor = new Proveedores();

                proveedor.setNombre(nombre);
                proveedor.setNumeroTelefono(numero);

                List<Proveedores> allProveedores = proveedoresDao.getAll();

                boolean f = true;

                for (int i = 0; i < allProveedores.size(); i++) {
                    if (allProveedores.get(i).getNombre() == proveedor.getNombre()
                            && allProveedores.get(i).getNumeroTelefono() == proveedor.getNumeroTelefono()) {
                        f = false;
                        break;
                    }
                }

                if (f) {
                    proveedoresDao.create(proveedor);
                }

            } else {
                model.addAttribute("isGood", false);
            }
        }

        return "Proveedores/introducirProveedor";
    }

    @GetMapping({"/categorias"})
    public String categorias(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        model.addAttribute("tipo", "Categorias");

        model.addAttribute("datos", categoriasDao.getAll());

        return "Categorias/categorias";
    }

    @GetMapping({"/introducirCategoria"})
    public String introducirCategoria(@RequestParam(value = "nombre", required = false) String nombre,
                                      Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());
        model.addAttribute("isGood", true);

        if (nombre != null) {
            if (!nombre.equals("")) {
                Categorias categoria = new Categorias();

                categoria.setNombre(nombre);

                List<Categorias> allCategorias = categoriasDao.getAll();

                boolean f = true;

                for (int i = 0; i < allCategorias.size(); i++) {
                    if (allCategorias.get(i).getNombre() == categoria.getNombre()) {
                        f = false;
                        break;
                    }
                }

                if (f) {
                    categoriasDao.create(categoria);
                }

            } else {
                model.addAttribute("isGood", false);
            }
        }

        return "Categorias/introducirCategoria";
    }

    @GetMapping({"/detalleProveedor"})
    public String detalleProveedor(@RequestParam(value = "id", defaultValue = "-1", required = false) int id,
                                   @RequestParam(value = "nombre", required = false) String nombre,
                                   @RequestParam(value = "numero", required = false) String numero,
                                   @RequestParam(value = "idProveedor", defaultValue = "-1", required = false) int idProveedor,
                                   @RequestParam(value = "idProducto", defaultValue = "-1", required = false) int idProducto,
                                   @RequestParam(value = "accion", defaultValue = "false", required = false) boolean accion,
                                   Model model) throws IOException, JSONException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());
        model.addAttribute("isGood", true);

        model.addAttribute("idProveedor", id);
        model.addAttribute("num", numero);

        if (id != -1) {
            if (proveedoresDao.getById(id) != null) {

                Proveedores p = proveedoresDao.getById(id).get(0);

                if (nombre != null) {
                    if (!nombre.equals(p.getNombre()) || !numero.equals(p.getNumeroTelefono())) {
                        if (!nombre.equals("") && !numero.equals("")) {
                            p.setNombre(nombre);
                            p.setNumeroTelefono(numero);

                            proveedoresDao.update(p);
                        } else {
                            model.addAttribute("isGood", false);
                        }
                    }
                }

                model.addAttribute("datos", productoDao.getByProveedor(id));

                model.addAttribute("name", p.getNombre());
                model.addAttribute("num", p.getNumeroTelefono());
            }
        }

        List<Productos> productosProveedor = proveedoresDao.getProductoByProveedor(id, bar.getId());

        ArrayList<ProductoProveedor> fkProductoProveedor = new ArrayList<>();

        List<FkProductosproveedores> ppl = productoProveedorDao.getAllByProveedor(id, bar.getId());

        if (ppl.size() != 0) {
            for (int i = 0; i < productosProveedor.size(); i++) {
                ProductoProveedor pp = new ProductoProveedor(productosProveedor.get(i).getId(),
                        productosProveedor.get(i).getNombre(), productosProveedor.get(i).getImg(),
                        ppl.get(i).getPrecio(),
                        categoriasDao.getById(productosProveedor.get(i).getFkCategoria()).get(0).getNombre(),
                        ppl.get(i).getIva());

                fkProductoProveedor.add(pp);
            }
        }

        List<Productos> productos = productoDao.getAll();

        if (fkProductoProveedor.size() != 0) {
            boolean f = false;
            boolean f2 = false;
            while (!f) {
                for (int i = 0; i < productos.size(); i++) {
                    for (int j = 0; j < productosProveedor.size(); j++) {
                        if (productos.get(i) == productosProveedor.get(j)) {
                            productos.remove(i);
                            f2 = true;
                            break;
                        }
                        f = true;
                    }
                    if (f2) {
                        f = false;
                        f2 = false;
                        break;
                    }
                }
            }
        }

        model.addAttribute("datos1", fkProductoProveedor);
        model.addAttribute("datos2", productos);


        return "Proveedores/detalleProveedor";
    }

    @GetMapping("/anadirProductoProveedor")
    public String anadirProductoProveedor(@RequestParam(value = "idProveedor", defaultValue = "-1", required = false) int idProveedor,
                                          @RequestParam(value = "idProducto", defaultValue = "-1", required = false) int idProducto,
                                          @RequestParam(value = "precio", defaultValue = "-1", required = false) double precio,
                                          @RequestParam(value = "accion", defaultValue = "-1", required = false) int accion,
                                          @RequestParam(value = "iva", defaultValue = "0", required = false) double iva) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        if (accion == 1) {
            FkProductosproveedores fkProductosproveedores = new FkProductosproveedores();

            fkProductosproveedores.setFkProveedor(idProveedor);
            fkProductosproveedores.setFkProducto(idProducto);
            fkProductosproveedores.setPrecio(precio);
            fkProductosproveedores.setFkBar(bar.getId());
            fkProductosproveedores.setIva(iva);

            productoProveedorDao.create(fkProductosproveedores);

            Comparativaprecio comparativaprecio = new Comparativaprecio();

            comparativaprecio.setFkProveedor(idProveedor);
            comparativaprecio.setFkProducto(idProducto);
            comparativaprecio.setPrecio(precio);
            comparativaprecio.setFkBar(bar.getId());
            comparativaprecio.setFecha(new Date(System.currentTimeMillis()));

            comparativaPrecioDao.create(comparativaprecio);
        } else if (accion == 2) {
            FkProductosproveedores fkProductosproveedores = new FkProductosproveedores();

            fkProductosproveedores.setId(productoProveedorDao.getByProductId(idProducto, bar.getId()).get(0).getId());
            fkProductosproveedores.setFkProveedor(idProveedor);
            fkProductosproveedores.setFkProducto(idProducto);
            fkProductosproveedores.setPrecio(precio);
            fkProductosproveedores.setFkBar(bar.getId());

            productoProveedorDao.delete(fkProductosproveedores);
        } else if (accion == 3) {
            FkProductosproveedores fkProductosproveedores = new FkProductosproveedores();

            fkProductosproveedores.setId(productoProveedorDao.getByProductId(idProducto, bar.getId()).get(0).getId());
            fkProductosproveedores.setFkProveedor(idProveedor);
            fkProductosproveedores.setFkProducto(idProducto);
            fkProductosproveedores.setPrecio(precio);

            productoProveedorDao.update(fkProductosproveedores);

            Comparativaprecio comparativaprecio = new Comparativaprecio();

            comparativaprecio.setFkProveedor(idProveedor);
            comparativaprecio.setFkProducto(idProducto);
            comparativaprecio.setPrecio(precio);
            comparativaprecio.setFkBar(bar.getId());
            comparativaprecio.setFecha(new Date(System.currentTimeMillis()));

            comparativaPrecioDao.create(comparativaprecio);
        }

        return "redirect:detalleProveedor?id=" + idProveedor;
    }

    @GetMapping({"/detalleCategoria"})
    public String detalleCategoria(@RequestParam(value = "id", defaultValue = "-1", required = false) int id,
                                   @RequestParam(value = "nombre", required = false) String nombre,
                                   Model model) throws IOException, JSONException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());
        model.addAttribute("isGood", true);

        model.addAttribute("id", id);

        if (id != -1) {
            if (categoriasDao.getById(id) != null) {

                Categorias c = categoriasDao.getById(id).get(0);

                if (nombre != null) {
                    if (!nombre.equals(c.getNombre())) {
                        if (!nombre.equals("")) {
                            c.setNombre(nombre);

                            categoriasDao.update(c);
                        } else {
                            model.addAttribute("isGood", false);
                        }
                    }
                }

                model.addAttribute("name", c.getNombre());
            }
        }

        return "Categorias/detalleCategoria";
    }

    @GetMapping({"/misProveedores"})
    public String misProveedores(Model model,
                                 @RequestParam(value = "accion", required = false) boolean accion,
                                 @RequestParam(value = "id", defaultValue = "-1", required = false) int id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());
        model.addAttribute("idProveedor", id);

        model.addAttribute("tipo", "Mis Proveedores");

        List<Proveedores> proveedores = proveedoresDao.getAll();

        List<Proveedores> misProveedores = proveedoresDao.getByBar(bar.getId());

        if (misProveedores.size() != 0) {
            boolean f = false;
            boolean f2 = false;
            while (!f) {
                for (int i = 0; i < proveedores.size(); i++) {
                    for (int j = 0; j < misProveedores.size(); j++) {
                        if (proveedores.get(i) == misProveedores.get(j)) {
                            proveedores.remove(i);
                            f2 = true;
                            break;
                        }
                        f = true;
                    }
                    if (f2) {
                        f = false;
                        f2 = false;
                        break;
                    }
                }
            }
        }

        if (id != -1) {
            if (accion) {
                misProveedores.add(proveedoresDao.getById(id).get(0));
                proveedores.remove(proveedoresDao.getById(id).get(0));

                FkBarproveedor fkBarproveedor = new FkBarproveedor();

                fkBarproveedor.setFkBar(bar.getId());
                fkBarproveedor.setFkProveedor(id);

                barProveedorDao.create(fkBarproveedor);
            } else {
                misProveedores.remove(proveedoresDao.getById(id).get(0));
                proveedores.add(proveedoresDao.getById(id).get(0));

                barProveedorDao.delete(barProveedorDao.getByProveedor(id).get(0));
            }
        }

        model.addAttribute("datos", proveedores);

        model.addAttribute("proveedor", misProveedores);

        return "Proveedores/misProveedores";
    }

    @GetMapping("/introducirCodigoTrazabilidad")
    public String introducirCodigoTrazabilidad(Model model,
                                               @RequestParam(value = "idProducto", required = false) int idProducto,
                                               @RequestParam(value = "codigoTrazabilidad", required = false) String codigoTrazabilidad) {

        model.addAttribute("introducir", "Introducir codigo de trazabilidad de " + productoDao.getById(idProducto).get(0).getNombre());
        model.addAttribute("id", idProducto);
        model.addAttribute("idGood", true);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        if (codigoTrazabilidad != null) {
            if (!codigoTrazabilidad.replace(" ", "").equals("")) {
                Codigostrazabilidad ct = new Codigostrazabilidad();

                ct.setFkProducto(idProducto);
                ct.setCodigoTrazabilidad(codigoTrazabilidad);
                ct.setFkBar(bar.getId());

                codigosTrazabilidadDao.create(ct);

                return "redirect:listaCodigosProducto";
            }
        }

        return "CodigosTrazabilidad/introducirCodigoTrazabilidad";
    }

    @GetMapping("/listaProductoIntroducirCodigo")
    public String listaProductoIntroducirCodigo(Model model) {

        model.addAttribute("datos", productoDao.getAll());

        return "CodigosTrazabilidad/listaProductosIntroducirCodigosTrazabilidad";
    }

    @GetMapping("/listaCodigosProducto")
    public String listaCodigosProducto(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        model.addAttribute("nombre", bar.getNombre());
        model.addAttribute("fotoPerfil",datosbarDao.getDatosByBarId(bar.getId()).get(0).getFotoPerfil());

        List<Productos> productosList = productoDao.getAll();

        ArrayList<CodigoTrazabilidadProducto> codigoTrazabilidadProductos = new ArrayList<>();

        if (productosList.size() != 0) {
            int num = 0;
            for (int i = 0; i < productosList.size(); i++) {
                List<Codigostrazabilidad> codigostrazabilidadList = codigosTrazabilidadDao.getByProductoIdBar(productosList.get(i).getId(), bar.getId());
                if (codigostrazabilidadList.size() != 0) {
                    num++;
                    String nombreProducto = productosList.get(i).getNombre();
                    ArrayList<String> listaCodigos = new ArrayList<>();
                    for (int j = 0; j < codigostrazabilidadList.size(); j++) {
                        listaCodigos.add(codigostrazabilidadList.get(j).getCodigoTrazabilidad());
                    }
                    codigoTrazabilidadProductos.add(new CodigoTrazabilidadProducto(num, nombreProducto, listaCodigos));
                }
            }
        }

        model.addAttribute("datos", codigoTrazabilidadProductos);

        return "CodigosTrazabilidad/listaCodigos";
    }

    @GetMapping("/perfil")
    public String perfil(Model model,
                         @RequestParam(value = "img", required = false) String img,
                         @RequestParam(value = "correo", required = false) String correo) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (currentUserName(authentication).equals("anonymousUser")) {
            return "redirect:register";
        }

        Bar bar = barDao.getByUserName(currentUserName(authentication)).get(0);

        Datosbar datosbar = datosbarDao.getDatosByBarId(bar.getId()).get(0);

        if (img != null) {
            if (!img.equals(datosbar.getFotoPerfil())) {
                datosbar.setFotoPerfil(img);
                datosbarDao.update(datosbar);
            }
        }

        if (correo != null) {
            if (!correo.equals(datosbar.getCorreoElectronico())) {
                datosbar.setCorreoElectronico(correo);
                datosbarDao.update(datosbar);
            }
        }

        model.addAttribute("nombre",bar.getNombre());
        model.addAttribute("correo",datosbar.getCorreoElectronico());
        model.addAttribute("fotoPerfil",datosbar.getFotoPerfil());

        return "Perfil/perfil";

    }

}
