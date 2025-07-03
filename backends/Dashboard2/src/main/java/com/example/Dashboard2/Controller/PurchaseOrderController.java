package com.example.Dashboard2.Controller;

import com.example.Dashboard2.Entity.PurchaseOrder;
import com.example.Dashboard2.Service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase_orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // Create or update purchase order
    @PostMapping("/save")
    public PurchaseOrder createOrUpdatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        return purchaseOrderService.savePurchaseOrder(purchaseOrder);
    }

    // Get all purchase orders
    @GetMapping("/getAll")
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @PutMapping("/edit/{id}")
    public PurchaseOrder updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder updatedOrder) {
        return purchaseOrderService.editPurchaseOrder(id, updatedOrder);
    }

    // Delete purchase order by ID
    @DeleteMapping("/delete/{id}")
    public String deletePurchaseOrder(@PathVariable Long id) {
        return purchaseOrderService.deletePurchaseOrder(id);
    }

    // Delete all purchase orders
    @DeleteMapping("/deleteAll")
    public String deleteAllPurchaseOrders() {
        return purchaseOrderService.deleteAllPurchaseOrders();
    }
}
